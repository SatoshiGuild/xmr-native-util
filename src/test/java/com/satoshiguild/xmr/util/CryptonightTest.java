/*
 * MIT License
 *
 * Copyright (c) 2018 "Satoshi Guild"
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.satoshiguild.xmr.util;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import static com.satoshiguild.xmr.util.TestUtil.hexStringToByteArray;
import static org.junit.Assert.assertArrayEquals;

public class CryptonightTest {
    @Test
    public void testCryptonight() {
        new BufferedReader(new InputStreamReader(CryptonightTest.class.getResourceAsStream("./cryptonight-tests.txt")))
                .lines()
                .filter(line -> !line.startsWith("#"))
                .forEach(line -> {
                    String[] parts = line.split(" ");
                    int variant = Integer.parseInt(parts[0]);
                    byte[] expected = hexStringToByteArray(parts[1]);
                    byte[] data = hexStringToByteArray(parts[2]);
                    assertArrayEquals(expected, Cryptonight.slowHash(data, variant));
                });
    }

}
