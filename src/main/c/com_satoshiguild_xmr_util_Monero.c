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
#include <jni.h>
#include <stdio.h>

#include "com_satoshiguild_xmr_util_Monero.h"

extern void cn_slow_hash(const void *data, size_t length, char *hash, int variant, int prehashed);

JNIEXPORT void JNICALL Java_com_satoshiguild_xmr_util_Monero_cryptonight
  (JNIEnv *env, jclass cls, jbyteArray in, jbyteArray out, jint variant) {
  jsize inLen = (*env)->GetArrayLength(env, in);
  // jsize outLen = (*env)->GetArrayLength(env, out);
  jbyte *inData = (*env)->GetByteArrayElements(env, in, 0);
  jbyte *outData = (*env)->GetByteArrayElements(env, out, 0);

  cn_slow_hash((char *) inData, inLen, (char *) outData, variant, 0);

  (*env)->ReleaseByteArrayElements(env, in, inData, JNI_ABORT);
  (*env)->ReleaseByteArrayElements(env, out, outData, 0);

  return;
}