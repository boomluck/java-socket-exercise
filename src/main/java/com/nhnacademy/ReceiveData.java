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
import java.util.Scanner;

public class ReceiveData {
    public static void main(String[] args) {
        String host = "localhost";
        int port = 12345;
        // TODO#1-1: 소켓을 try-with-resources로 생성합니다.
        // TODO#1-2: 수신을 위한 Scanner을 생성합니다. Scanner에 사용할 InputStream은 소켓에서
        // 얻어옵니다.
        try (Socket socket = null;
                Scanner socketIn = null) {
            System.out.println("메시지 수신을 위한 소켓이 연결되었습니다.");

            while (!Thread.currentThread().isInterrupted()) {
                System.out.println("메시지를 기다립니다.");
                // TODO#1-3: 소켓을 통해 문자열을 받습니다.
                String line = null;
                // 빈 문자열이 오면 종료합니다.
                if (line.isEmpty()) {
                    System.out.println("빈 메시지를 수신하여 종료합니다.");
                    break;
                }
                System.out.print("메시지를 수신하였습니다: ");
                System.out.println(line);
            }

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
