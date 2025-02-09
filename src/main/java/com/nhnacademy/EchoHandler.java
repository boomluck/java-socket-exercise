package com.nhnacademy;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;

import lombok.extern.slf4j.Slf4j;

/**
 * EchoHandler는 클라이언트와 서버 간의 통신을 처리하는 스레드입니다.
 *
 * @author NHN Academy Corp.
 */
@Slf4j
public class EchoHandler extends Thread {
    /**
     * 모든 EchoHandler 인스턴스를 저장하는 정적 맵입니다.
     * 키는 스레드 ID, 값은 EchoHandler 인스턴스입니다.
     */
    static Map<Long, EchoHandler> handlerMap = new HashMap<>();
    Socket socket;
    Scanner socketIn;
    PrintStream socketOut;

    /**
     * EchoHandler를 생성합니다.
     *
     * @param socket 클라이언트와의 연결을 나타내는 소켓입니다.
     * @throws IOException 소켓 입력/출력 스트림을 생성하는 데 실패하면 발생합니다.
     */
    public EchoHandler(Socket socket) throws IOException {
    }

    /**
     * 클라이언트와의 통신을 처리하는 메소드입니다.
     *
     * <p>
     * 클라이언트와의 연결을 맺고, 클라이언트가 전송하는 메시지를 수신하여 처리합니다.
     * </p>
     *
     * <p>
     * 처리 과정은 다음과 같습니다.
     * </p>
     *
     * <ol>
     * <li>클라이언트와의 연결을 맺고, EchoHandler 인스턴스를 handlerMap에 등록합니다.</li>
     * <li>클라이언트가 전송하는 메시지를 수신하여 처리합니다.</li>
     * <li>수신한 메시지를 모든 클라이언트에게 전달합니다.</li>
     * <li>클라이언트가 연결을 끊을 때까지 위 과정을 반복합니다.</li>
     * <li>클라이언트가 연결을 끊으면, EchoHandler 인스턴스를 handlerMap에서 제거하고 소켓을 닫습니다.</li>
     * </ol>
     */
    @Override
    public void run() {
    }

    /**
     * 메시지를 전송한 클라이언트의 ID를 포함해 메시지를 전송합니다.
     *
     * @param id 메시지를 전송한 클라이언트의 ID입니다.
     * @param message 전송할 메시지입니다.
     */
    public void sendToOtherClients(long id, String message) {
    }

    /**
     * 모든 클라이언트에게 메시지를 전송합니다.
     * 단, 메시지를 보낸 클라이언트는 제외합니다.
     *
     * @param message 방송할 메시지입니다.
     */
    static void broadcast(String message) {
    }
}
