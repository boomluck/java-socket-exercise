package com.nhnacademy;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Scanner;
import java.util.UUID;

import lombok.extern.slf4j.Slf4j;

/**
 * ClientHandler 클래스는 클라이언트와의 통신을 담당하는 스레드입니다.
 */
@Slf4j
public class ClientHandler extends Thread {
    static Map<String, ClientHandler> handlerMap = new HashMap<>();
    Socket socket;
    Scanner socketIn;
    PrintStream socketOut;
    String clientId = null;
    String userId = null;

    /**
     * ClientHandler 생성자는 소켓을 받아서 초기화합니다.
     *
     * @param socket 클라이언트와의 소켓
     * @throws IOException 소켓 생성에 실패한 경우
     */
    public ClientHandler(Socket socket) throws IOException {
        if (Objects.isNull(socket)) {
            throw new IllegalArgumentException();
        }

        clientId = UUID.randomUUID().toString();
        this.socket = socket;
        socketIn = new Scanner(socket.getInputStream());
        socketOut = new PrintStream(socket.getOutputStream());
    }

    /**
     * 클라이언트 ID를 반환합니다.
     *
     * @return 클라이언트 ID
     */
    public String getClientId() {
        return clientId;
    }

    /**
     * 로그인 여부를 확인합니다.
     *
     * @return 로그인 여부
     */
    public boolean isLoggedIn() {
        return userId != null;
    }

    /**
     * 사용자 ID를 반환합니다.
     *
     * @return 사용자 ID
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 사용자 ID를 설정합니다.
     *
     * @param userId 설정할 사용자 ID
     */
    void setUserId(String userId) {
        if (Objects.isNull(userId)) {
            throw new IllegalArgumentException();
        }

        this.userId = userId;
    }

    @Override
    public void run() {
        log.debug("응답을 시작합니다.");
        handlerMap.put(String.valueOf(threadId()), this);

        try {
            while (!isInterrupted()) {
                log.debug("메시지를 기다립니다.");
                String line = socketIn.nextLine();
                if (line.isEmpty()) {
                    log.debug("빈 메시지를 받았습니다. 연결을 끊습니다.");
                    break;
                }

                if (line.charAt(0) == '@') {
                    String[] fields = line.split("\\s", 2);
                    if (fields.length != 2) {

                    }
                    switch (fields[0]) {
                        case "@login": {
                            if (isLoggedIn()) {
                                send("이미 로그인되어 있습니다.");
                            } else if (isLoggedIn(fields[1])) {
                                send("동일한 id가 존재합니다. 다시 시도해 주세요.");
                            } else {
                                setUserId(fields[1]);
                                send("로그인 성공.");
                                log.debug(" 클라이언트 ID가 등록되었습니다: {}", getUserId());
                            }
                        }
                            break;

                        case "@list": {
                            if (isLoggedIn()) {
                                StringBuilder builder = new StringBuilder();

                                handlerMap.forEach((k, v) -> {
                                    if (!getUserId().equals(v.getUserId())) {
                                        builder.append(v.getUserId()).append(System.lineSeparator());
                                    }
                                });

                                send(builder.toString());
                            } else {
                                throw new NotLoggedInException("로그인 후 전송 가능합니다.");
                            }
                        }
                            break;

                        default: {
                            if (isLoggedIn()) {
                                send(fields[0].substring(1), fields[1]);
                            } else {
                                throw new NotLoggedInException("로그인 후 전송 가능합니다.");
                            }
                        }
                    }
                } else {
                    if (isLoggedIn()) {
                        sendAll(line);
                    } else {
                        throw new NotLoggedInException("로그인 후 전송 가능합니다.");
                    }
                }
            }
        } catch (NotLoggedInException e) {
            send(e.getMessage());
        } catch (NoSuchElementException e) {
            log.debug("클라이언트에서 연결을 끊었습니다.");
        }

        log.debug("프로그램을 종료합니다.");

        handlerMap.remove(getUserId());

        socketIn.close();
        socketOut.close();
    }

    /**
     * 메시지를 클라이언트로 전송합니다.
     *
     * @param message 전송할 메시지
     */
    public void send(String message) {
        if (Objects.isNull(message)) {
            throw new IllegalArgumentException();
        }

        socketOut.printf("%s%n", message);
    }

    /**
     * 특정 사용자에게 메시지를 전송합니다.
     *
     * @param targetId 대상 사용자 ID
     * @param message  전송할 메시지
     */
    void send(String targetId, String message) {
        if (Objects.isNull(targetId) || Objects.isNull(message)) {
            throw new IllegalArgumentException();
        }

        ClientHandler targetHandler = getHandler(targetId);
        if (Objects.isNull(targetHandler)) {
            send("사용자가 존재하지 않습니다: " + targetId);
        } else {
            targetHandler.send(getUserId() + ": " + message);
        }
    }

    /**
     * 모든 사용자에게 메시지를 전송합니다.
     *
     * @param message 전송할 메시지
     */
    void sendAll(String message) {
        if (Objects.isNull(message)) {
            throw new IllegalArgumentException();
        }

        handlerMap.forEach((k, v) -> {
            if (!getUserId().equals(v.getUserId())) {
                v.send(getUserId() + ": " + message);
            }
        });
    }

    /**
     * 특정 사용자가 로그인되어 있는지 확인합니다.
     *
     * @param userId 확인할 사용자 ID
     * @return 로그인 여부
     */
    static boolean isLoggedIn(String userId) {
        if (Objects.isNull(userId)) {
            throw new IllegalArgumentException();
        }

        return handlerMap.values().stream().anyMatch(h -> userId.equals(h.getUserId()));
    }

    /**
     * 사용자 ID로 ClientHandler를 찾습니다.
     *
     * @param userId 찾을 사용자 ID
     * @return 찾은 ClientHandler 또는 null
     */
    static ClientHandler getHandler(String userId) {
        if (Objects.isNull(userId)) {
            throw new IllegalArgumentException();
        }

        return handlerMap.values().stream().filter(h -> userId.equals(h.getUserId())).findFirst().orElse(null);
    }
}
