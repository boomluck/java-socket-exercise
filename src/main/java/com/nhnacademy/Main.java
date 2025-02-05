package com.nhnacademy;

import java.io.IOException;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

public class Main {
    static final int MIN_PORT = 1;
    static final int MAX_PORT = 65535;

    public static List<Integer> portScanner(String host, int start, int end) {
        // TODO: 시작과 끝을 반대로 입력한 경우, 변경해 줍니다.

        // TODO: 인수를 검증합니다. host는 null이 아니어야 하고, 포트는 1 ~ 65535내에서만 가능합니다.
        List<Integer> portList = new LinkedList<>();

        for (int port = start; port <= end; port++) {
            // TODO: 소켓을 생성해 정상적으로 연결되는 경우, 포트를 저장합니다.
        }

        return portList;
    }

    public static void main(String[] args) {
        String host = "localhost";
        // TODO: 찾고자 하는 포트 범위의 시작을 지정하세요.
        int startPort = MIN_PORT;
        // TODO: 찾고자 하는 포트 범위의 마지막을 지정하세요.
        int endPort = MAX_PORT;

        List<Integer> listenPortList = portScanner(host, startPort, endPort);
        if (!listenPortList.isEmpty()) {
            for (Integer port : listenPortList) {
                System.out.printf("포트[%5d]가 열려 있습니다.%n", port);
            }
        } else {
            System.out.printf("요청하신 범위[%d ~ %d]내에 열려 있는 포트가 없습니다.%n", startPort, endPort);
        }
    }
}
