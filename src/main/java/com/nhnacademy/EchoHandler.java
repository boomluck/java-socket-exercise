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

        // TODO#2: socket에서 데이터를 읽고 쓰기 위한 IOStream을 생성한다.
        try (Scanner socketIn = null;
                PrintStream socketOut = null) {

            while (!Thread.currentThread().isInterrupted()) {
                log.debug("메시지를 기다립니다.");
                // TODO#3: socket에서 데이터를 읽어들인다.
                String line = null;
                if (line.isEmpty()) {
                    log.debug("빈 메시지를 받았습니다. 연결을 끊습니다.");
                    break;
                }
                log.debug("메시지를 받았습니다: {}", line);
                // TODO#4: 읽어 들인 메시지를 돌려 보낸다.
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
