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
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Echo Server
 *
 */
public class ReconnectableServer {
    public static void echoHandler(Socket socket) {
        if (socket == null) {
            throw new IllegalArgumentException();
        }

        try (Scanner socketIn = new Scanner(socket.getInputStream());
                PrintStream socketOut = new PrintStream(socket.getOutputStream())) {

            while (!Thread.currentThread().isInterrupted()) {
                System.out.println("메시지를 기다립니다.");
                String line = socketIn.nextLine();
                if (line.isEmpty()) {
                    System.out.println("빈 메시지를 받았습니다. 연결을 끊습니다.");
                    break;
                }
                System.out.print("메시지를 받았습니다: ");
                System.out.println(line);
                socketOut.println(line);
                System.out.println("메시지를 보냈습니다.");
            }
        } catch (NoSuchElementException e) {
            System.out.println("클라이언트에서 연결을 끊었습니다.");
        } catch (IOException e) {
            System.err.println("데이터 수신중 오류가 발생하였습니다: " + e.getMessage());
        }
        System.out.println("프로그램을 종료합니다.");
    }

    public static void main(String[] args) {
        int port = 12345;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("소켓을 생성하였습니다.");
            while (!Thread.currentThread().isInterrupted()) {
                System.out.println("클라이언트 연결을 기다립니다.");
                try (Socket socket = serverSocket.accept()) {
                    System.out.printf("클라이언트가 연결되었습니다.%n", socket.getInetAddress().getHostAddress(), socket.getPort());
                    echoHandler(socket);
                    System.out.println("클라이언트완 연결이 끊어졌습니다.");
                }
            }
        } catch (IOException e) {
            System.err.println("연결에 오류가 발생하였습니다: " + e.getMessage());
        }
    }
}
