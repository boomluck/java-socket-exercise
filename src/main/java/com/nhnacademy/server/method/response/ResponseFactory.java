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

package com.nhnacademy.server.method.response;

import com.nhnacademy.server.method.response.exception.ResponseNotFoundException;
import com.nhnacademy.server.method.response.impl.EchoResponse;

import java.util.ArrayList;
import java.util.Objects;

public class ResponseFactory {
    private static final ArrayList<Response> responseList = new ArrayList<>() {
        {
            // TODO#1-8 EchoResponse 객체를 성성해서 추가 합니다.

        }
    };

    public static Response getResponse(String method) {
        /*
         * TODO#1-9 responseList에서 parameter로 전달된 method에 해당된 구현체를 반환 합니다.
         * response가 존재하지 않다면 ResponseNotFoundException을 발생합니다.
         */

        return null;
    }
}
