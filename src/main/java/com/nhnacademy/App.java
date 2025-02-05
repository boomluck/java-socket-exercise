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
public class App {
    public static void echoHandler(Socket socket) {
        try (/* TODO:  소켓에서 InputStream/OutputStream을 얻어내 수신 및 송신에 적용합니다. */) {

            while (!Thread.currentThread().isInterrupted()) {
                // TODO: nextLine을 이용해 줄 문자열을 받습니다.
                // TODO: 받은 문자열은 돌려 보냅니다.
            }

        } catch (IOException e) {
            System.err.println("데이터 수신중 오류가 발생하였습니다: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        int port = 12345;
        try (/* TODO: 서버 소켓을 생성합니다. */
             /* TODO: 서버 소켓 객체의 accept() 메소드를 이용해 접속을 기다리며, 클라이언트 접속시 반환되는 소켓을 이용합니다. */) {

            echoHandler(socket);
        } catch (IOException e) {
            System.err.println("연결에 오류가 발생하였습니다: " + e.getMessage());
        }
    }
}
