package com.nhnacademy;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EchoHandler implements Runnable {
    static Map<Long, EchoHandler> handlerMap = new HashMap<>();
    Socket socket;
    Scanner socketIn;
    PrintStream socketOut;

    public EchoHandler(Socket socket) throws IOException {
        this.socket = socket;
        socketIn = new Scanner(socket.getInputStream());
        socketOut = new PrintStream(socket.getOutputStream());

    }

    @Override
    public void run() {
        log.debug("응답을 시작합니다.");
        handlerMap.put(Thread.currentThread().threadId(), this);

        try {
            while (!Thread.currentThread().isInterrupted()) {
                log.debug("메시지를 기다립니다.");
                String line = socketIn.nextLine();
                if (line.isEmpty()) {
                    log.debug("빈 메시지를 받았습니다. 연결을 끊습니다.");
                    break;
                }
                log.debug("메시지를 받았습니다: {}", line);
                broadcast(line);
                log.debug("메시지를 보냈습니다.");
            }
        } catch (NoSuchElementException e) {
            log.debug("클라이언트에서 연결을 끊었습니다.");
        }

        log.debug("프로그램을 종료합니다.");

        handlerMap.remove(Thread.currentThread().threadId());

        socketIn.close();
        socketOut.close();
    }

    public void send(String message) {
        socketOut.println(message);
    }

    static void broadcast(String message) {
        handlerMap.forEach((id, handler) -> handler.send(message));
    }
}
