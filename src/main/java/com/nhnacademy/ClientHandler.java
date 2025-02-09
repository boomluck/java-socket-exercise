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

    /**
     * ClientHandler 생성자는 소켓을 받아서 초기화합니다.
     *
     * @param socket 클라이언트와의 소켓
     * @throws IOException 소켓 생성에 실패한 경우
     */
    public ClientHandler(Socket socket) throws IOException {
    }


    @Override
    public void run() {
    }
}
