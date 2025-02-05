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

public class App {
    public static void main(String[] args) {
        String host = "localhost";
        int port = 12345;
        try (/* TODO: 서버와 통신을 위한 소켓을 생성합니다. 소켓이 정상적으로 생성되면, 소켓으로부터 데이터를 읽을 Scanner 객체를 생성합니다.*/) {

            while (!Thread.currentThread().isInterrupted()) {
                // TODO: Scanner를 이용해 서버에서 문자열을 받습니다.

                // TODO: 표준 출력 객체를 이용해 출력합니다.
            }

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
