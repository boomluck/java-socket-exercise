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
        // TODO#1-1: 인수의 유효성의 검사합니다. 유효하지 않을 경우, IllegalArgumentException()을 발생시킵니다.

        /*
         * TOOD#1-2: 소켓에서 데이터 송수신을 위한 I/O Stream을 가져와 관련 객체를 생성하고,
         * 클라이언트와 데이터를 주고 받습니다.
         */
    }

    public static void main(String[] args) {
        int port = 12345;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("소켓을 생성하였습니다.");
            while (!Thread.currentThread().isInterrupted()) {
                System.out.println("클라이언트 연결을 기다립니다.");
                /*
                 * TODO#1-3: 클라이언트 접속을 기다리며, 접속하여 생성되는 소켓으로 메시지를 주고 받습니다.
                 * 연결이 끊어지면, 다시 접속을 기다립니다.
                 */

            }
        } catch (IOException e) {
            System.err.println("연결에 오류가 발생하였습니다: " + e.getMessage());
        }
    }
}
