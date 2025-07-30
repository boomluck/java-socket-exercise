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

package com.nhnacademy.server.runnable;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;

import com.nhnacademy.server.runnable.MessageServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * MessageServer 클래스의 테스트 클래스입니다.
 *
 * @author NHN Academy Corp.
 */
@Slf4j
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class MessageServerTest {

    static MessageServer messageServer;
    static Thread thread;

    /**
     * 모든 테스트가 시작되기 전에 실행됩니다.
     * MessageServer 인스턴스를 생성하고, 이를 실행하는 스레드를 시작합니다.
     */
    @BeforeAll
    static void beforeAllSetup() {
        // 전역 환경설정
        messageServer = new MessageServer(8888);
        thread = new Thread(messageServer);
        thread.start();
    }

    /**
     * MessageServer 생성자의 port < 0 검증 테스트입니다.
     */
    @Test
    @Order(1)
    @DisplayName("constructor : port <= 0")
    void constructorTest1() {
        // TODO#1-11 portt < 0 검증하는 코드를 작성하세요

    }

    /**
     * 이미 사용 중인 포트로 MessageServer를 시작하는 경우 RuntimeException이 발생하는지 검증합니다.
     */
    @Test
    @Order(2)
    @DisplayName("aready used port")
    void constructorTest2() {
        // TODO#1-12 이미 서버는 8888 port를 사용하고 있을 때 8888포트로 서버를 시작한다면 RuntimeException
        // 발생하는지 검증 합니다.

    }

    /**
     * echo 서버에 메시지를 전송하고, 서버가 동일한 메시지를 응답하는지 검증합니다.
     *
     * @throws IOException 소켓 통신 중 예외가 발생할 경우
     */
    @Test
    @Order(3)
    @DisplayName("echo message")
    void echoMessageTest() throws IOException {
        /*
         * TODO#1-13 8888 port를 사용하고 있는 echo 서버에 접근하기 위헤서 client socket을 생성 합니다.
         * - host : localhost
         * - port : 8888
         */
        Socket client = null;

        /*
         * TODO#1-14 간단한 test client 구현
         * - cleint 가 server에 메시지를 전송하기 위해서 PrintWriter 객체를 사용할 수 있도록 초기화 합니다.
         * - server가 전송하는 데이터를 받기 위해서 BufferedReader 객체를 초기화 합니다.
         */

        try (PrintWriter clientOut = null;
                BufferedReader clientIn = null;) {
            Assertions.assertAll(
                    () -> {
                        String message = "hello";
                        clientOut.println(message);
                        String actual = clientIn.readLine();
                        log.debug("actual:{}", actual);
                        Assertions.assertEquals(message, actual);
                    },
                    () -> {
                        // TODO#1-15 client가 server로 'java' message를 전송할 때 server가 client가 전송한 message를
                        // 응답하는지 검증하는 코드를 작성하세요
                        String message = "java";

                    },
                    () -> {
                        // TODO#1-16 '엔에이치엔아카데미' 검증하는 코드를 작성 하세요
                        String message = "엔에이치엔아카데미";

                    });
        }
    }

    /**
     * 모든 테스트가 종료되면 서버를 구동하고 있는 스레드를 종료합니다.
     */
    @AfterAll
    static void tearDown() {
        // TODO#1-17 모든 테스트가 종료되면 server를 구동하고있는 thread에 interrupt()를 발생시켜 종료 합니다.

    }

}