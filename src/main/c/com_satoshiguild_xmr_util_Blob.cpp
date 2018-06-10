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
#include <cstdint>
#include <string>
#include <iostream>

#include <jni.h>

#include "com_satoshiguild_xmr_util_Blob.h"

namespace cryptonote {
    struct block {
        uint8_t major_version;
        uint8_t minor_version;
        uint64_t timestamp;
        char prevHash[32];
        uint32_t nonce;
        char pad[600];
    };

    extern bool parse_and_validate_block_from_blob(const std::string& b_blob, block& b);

    extern std::string get_block_hashing_blob(const block& b);
}

using namespace cryptonote;

JNIEXPORT jbyteArray JNICALL Java_com_satoshiguild_xmr_util_Blob_getHashingBlob
  (JNIEnv *env, jobject obj) {
  jclass cls = env->GetObjectClass(obj);

  // get blob data
  jfieldID oDataID = env->GetFieldID(cls, "data", "[B");
  jobject oData = env->GetObjectField(obj, oDataID);
  jbyteArray bData = reinterpret_cast<jbyteArray>(oData);
  jsize dataLen = env->GetArrayLength(bData);
  jbyte *data = env->GetByteArrayElements(bData, nullptr);

  // cryptonote magic
  std::string input(reinterpret_cast<char *>(data), dataLen);
  std::string output;
  block bl = { 0 };

  if (!parse_and_validate_block_from_blob(input, bl)) {
    jclass ex = env->FindClass("com/satoshiguild/xmr/util/InvalidBlobException");
    env->ThrowNew(ex, "Invalid blob");
    return NULL;
  }
  output = get_block_hashing_blob(bl);

  // create return array
  jbyteArray ret = env->NewByteArray(output.size());
  env->SetByteArrayRegion(ret, 0, output.size(), reinterpret_cast<const jbyte *>(output.data()));

  // cleanup
  env->ReleaseByteArrayElements(bData, data, JNI_ABORT);

  return ret;
}