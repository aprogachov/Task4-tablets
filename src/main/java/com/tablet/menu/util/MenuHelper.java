package com.tablet.menu.util;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Scanner;

@Component
public class MenuHelper {

    private Scanner sc = new Scanner(System.in);

    public String read() {
        String result = null;
        while (StringUtils.isEmpty(result)) {
            result = sc.next();
        }

        return result;
    }

    public int readInt() {
        Integer result = null;

        while (result == null) {
            String next = sc.next();
            if ("".equals(next)) { //fix non-empty buffer on number reading
                next = sc.next();
            }

            try {
                result = Integer.valueOf(next);
            } catch (NumberFormatException e) {
                System.out.println("Input mismatch");

            }
        }

        return result;
    }

    public int readPosition(int max) {
        if (max < 1) {
            throw new IllegalArgumentException("Menu must have at least one element");
        }

        Integer result = -1;
        while (result < 1 || result > max) {
            result = readInt();
        }


        return result;
    }
}
