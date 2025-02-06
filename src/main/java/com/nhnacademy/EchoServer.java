/*
 * +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
 * + Copyright 2024. NHN Academy Corp. All rights reserved.
 * + * While every precaution has been taken in the preparation of this resource,  assumes no
 * + responsibility for errors or omissions, or for damages resulting from the use of the information
 * + contained herein
 * + No part of this resource may be reproduced, stored in a retrieval system, or transmitted, in any
 * + form or by any means, electronic, mechanical, photocopying, recording, or otherwise, without the
 * + prior written permission.
 * +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
 */

package com.nhnacademy;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * Echo Server
 *
 */
public class EchoServer {
    public static void echoHandler(Socket socket) {
        if (socket == null) {
            throw new IllegalArgumentException();
        }

        // TODO#1-1: 소켓에서 InputStream/OutputStream을 얻어내 수신 및 송신에 적용합니다
        try (Scanner socketIn = null;
                PrintStream socketOut = null) {

            while (!Thread.currentThread().isInterrupted()) {
                System.out.println("메시지를 기다립니다.");
                // TODO#1-2: nextLine을 이용해 문자열을 받습니다.
                String line = null;
                if (line.isEmpty()) {
                    System.out.println("빈 메시지를 받았습니다. 연결을 끊고, 프로그램을 종료합니다.");
                    break;
                }
                System.out.print("메시지를 받았습니다: ");
                System.out.println(line);
                // TODO#1-3: 받은 문자열은 돌려 보냅니다.
                System.out.println("메시지를 보냈습니다.");
            }

        } catch (IOException e) {
            System.err.println("데이터 수신중 오류가 발생하였습니다: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        int port = 12345;

        // TODO#1-4: 서버 소켓을 생성합니다.
        try (ServerSocket serverSocket = null) {
            System.out.println("소켓을 생성하였습니다. 클라이언트 연결을 기다립니다.");
            // TODO#1-5: 서버 소켓 객체의 accept() 메소드를 이용해 접속을 기다리며, 클라이언트 접속시 반환되는 소켓을 이용합니다.
            try (Socket socket = null) {
                System.out.printf("클라이언트가 연결되었습니다.%n", socket.getInetAddress().getHostAddress(), socket.getPort());
                echoHandler(socket);
            }
        } catch (IOException e) {
            System.err.println("연결에 오류가 발생하였습니다: " + e.getMessage());
        }
    }
}
