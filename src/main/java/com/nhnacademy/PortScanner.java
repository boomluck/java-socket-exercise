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
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

/**
 * Main 클래스는 포트 스캐너를 구현하는 클래스입니다.
 */
public class PortScanner {
    static final int MIN_PORT = 1;
    static final int MAX_PORT = 65535;

    String host;
    int startPort = MIN_PORT;
    int endPort = MAX_PORT;
    List<Integer> portList = new LinkedList<>();

    /**
     * PortScanner 클래스의 생성자입니다.
     *
     * @param host      호스트
     * @param startPort 포트 스캐닝을 시작할 포트
     * @param endPort   포트 스캐닝을 끝낼 포트
     */
    public PortScanner(String host, int startPort, int endPort) {
        // TODO#1-1: 시작과 끝을 반대로 입력한 경우, 변경해 줍니다.
        if (startPort > endPort) {
            int temp = startPort;
            startPort = endPort;
            endPort = temp;
        }
        // TODO#1-2: 인수를 검증합니다. host는 null이 아니어야 하고, 포트는 1 ~ 65535내에서만 가능합니다.
        // 인수 검증을 실패한 경우, IllegalArgumentException을 발생 시킵니다.
        if ((host == null) || (startPort < MIN_PORT) || (MAX_PORT < endPort)) {
            throw new IllegalArgumentException();
        }

        this.host = host;
        this.startPort = startPort;
        this.endPort = endPort;
    }

    /**
     * 현재 포트 목록을 반환합니다.
     *
     * @return 포트 목록
     */
    public List<Integer> getPortList() {
        return portList;
    }

    /**
     * 설정된 호스트와 포트 범위에 대해 포트 스캐닝을 수행합니다.
     *
     */
    public void scan() {

        // TODO#1-3: 이전에 스캔하여 찾은 목록을 삭제합니다.
        portList.clear();

        for (int port = startPort; port <= endPort; port++) {
            // TODO#1-4: 소켓을 생성해 정상적으로 연결되는 경우, 포트를 저장합니다.
            try {
                Socket socket = new Socket(host, port);

                portList.add(port);

                socket.close();
            } catch (IOException ignore) {
            }
        }
    }

    /**
     * Main 메소드는 포트 스캐너를 실행하는 메소드입니다.
     *
     * @param args 명령행 인수
     */
    public static void main(String[] args) {
        String host = "localhost";
        // TODO#2-1: 찾고자 하는 포트 범위의 시작을 지정하세요.
        int startPort = MIN_PORT;
        // TODO#2-2: 찾고자 하는 포트 범위의 마지막을 지정하세요.
        int endPort = MAX_PORT;

        PortScanner portScanner = new PortScanner(host, startPort, endPort);
        if (!portScanner.getPortList().isEmpty()) {
            for (Integer port : portScanner.getPortList()) {
                System.out.printf("포트[%5d]가 열려 있습니다.%n", port);
            }
        } else {
            System.out.printf("요청하신 범위[%d ~ %d]내에 열려 있는 포트가 없습니다.%n", startPort, endPort);
        }
    }
}
