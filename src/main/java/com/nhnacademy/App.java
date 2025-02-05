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

public class App {
    static final int MIN_PORT = 1;
    static final int MAX_PORT = 65535;

    public static List<Integer> portScanner(String host, int start, int end) {
        if (start > end) {
            int temp = start;
            start = end;
            end = temp;
        }

        if (host == null || (start < MIN_PORT || MAX_PORT < end)) {
            throw new IllegalArgumentException();
        }

        List<Integer> portList = new LinkedList<>();

        for (int port = start; port <= end; port++) {
            try {
                // TODO: host와 포트를 이용해 소켓을 생성합니다.
                // TODO: 정상적으로 소켓 객체가 생성될 경우, 포트를 저장하고 명시적으로 close()는 호출하지 않습니다.
            } catch (IOException ignore) {
            }
        }

        return portList;
    }

    public static void main(String[] args) {
        String host = "localhost";
        int startPort = 1000;
        int endPort = 10000;

        List<Integer> listenPortList = portScanner(host, startPort, endPort);
        if (!listenPortList.isEmpty()) {
            for (Integer port : listenPortList) {
                System.out.printf("포트[%5d]가 열려 있습니다.%n", port);
            }
        } else {
            System.out.printf("요청하신 범위[%d ~ %d]내에 열려 있는 포트가 없습니다.%n", startPort, endPort);
        }

        try {
            System.in.read();
        } catch (IOException e) {
        }
    }
}
