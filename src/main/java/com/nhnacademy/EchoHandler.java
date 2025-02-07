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

    public EchoHandler(Socket socket) {
        this.socket = socket;

    }

    @Override
    public void run() {
        log.debug("응답을 시작합니다.");
        handlerMap.put(Thread.currentThread().threadId(), this);

        try (Scanner socketIn = new Scanner(socket.getInputStream());
                PrintStream socketOut = new PrintStream(socket.getOutputStream())) {

            while (!Thread.currentThread().isInterrupted()) {
                log.debug("메시지를 기다립니다.");
                String line = socketIn.nextLine();
                if (line.isEmpty()) {
                    log.debug("빈 메시지를 받았습니다. 연결을 끊습니다.");
                    break;
                }
                log.debug("메시지를 받았습니다: {}", line);
                socketOut.println(line);
                log.debug("메시지를 보냈습니다.");
            }
        } catch (NoSuchElementException e) {
            log.debug("클라이언트에서 연결을 끊었습니다.");
        } catch (IOException e) {
            log.error("데이터 수신중 오류가 발생하였습니다: {}", e.getMessage());
        }
        log.debug("프로그램을 종료합니다.");

        handlerMap.remove(Thread.currentThread().threadId());
    }
}
