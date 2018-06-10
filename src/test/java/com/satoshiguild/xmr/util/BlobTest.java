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

import static com.satoshiguild.xmr.util.TestUtil.hexStringToByteArray;
import static org.junit.Assert.assertArrayEquals;

public class BlobTest {
    @Test
    public void testGetHashingBlob() {
        assertArrayEquals(new Blob(hexStringToByteArray("0707d7dcf0d8054493ab0d8b6599c3d11c23e90e69598c1fef2d8513d27a09dd06a0a90342d8b00000000002da916101ff9e916101cfbae6a7b78301028b9115963e93c44da6b37358562409649aff1b14f6d8a9674271e93595e06f855f01a2be60f21770b0185ff47bcb4627844deb7f5d5a6eb1ca293e0d70bf43fd568d023c000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000b80a7a97cff1ef670f535d6087f724ea7b4b3cc37098eac95682d724e90cea007d2c1a8389564d3890c30431404366f8e2f3ee491f1dcbfecd84a5cdeb1d724ae308fb17cc5ed29e6753e8f6794c19a1da0a0d9e9eedbad6408c0c19f7748002b034796271efbf13b677849c3e1392e5aeda959c3bf752e4ebc930b2e3f25888bc60d8047622f2976875bab7939f899bc89e3dcaac785529dc4fb3dc39e0f188f9b2bcd6127cf7c3c1b465f4fb306ba564e972c763905410f2e127334fb69665fd7883c44e7cd4c58fe529dce54db53d652315ec5fc62c56699d3e860d2a598a913cad6ad38072dac718ea4532de5ff535cd6d63ee9d80955ea8a1899f57f3a9d42bb4454e94499904e515117be0720cf304c9a3e3927ae7b62f6e2be18c7e8ed77aa8bccfe0b156d3fb654527d1a511b8cf0bfed99b6439465a829dee9d4271d60f7d124cddb8a69b151328dd083249e20341b805602b9619e6a55e8c85bb203")).getHashingBlob(), hexStringToByteArray("0707d7dcf0d8054493ab0d8b6599c3d11c23e90e69598c1fef2d8513d27a09dd06a0a90342d8b000000000bd42cdba19216661312de8c4938791bfc1af790a24b7013dbf163e91b7f121a30c"));
    }

    @Test(expected = InvalidBlobException.class)
    public void testGetInvalidHashingBlob() {
        new Blob(hexStringToByteArray("00")).getHashingBlob();
    }
}
