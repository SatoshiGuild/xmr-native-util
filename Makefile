JDK_HOME ?= /usr/lib/jvm/java-8-openjdk-amd64

CFLAGS := -O2 -Wall -fPIC -I $(JDK_HOME)/include -I $(JDK_HOME)/include/linux
CXXFLAGS := $(CFLAGS)
LDFLAGS := -fPIC -shared -lcryptonote_basic -lcncrypto

OBJECTS := \
	src/main/c/com_satoshiguild_xmr_util_Blob.o \
	src/main/c/com_satoshiguild_xmr_util_Cryptonight.o

TARGET := target/classes/libmonero.so

.PHONY: all clean

all: $(TARGET)

clean:
	$(RM) $(OBJECTS) $(TARGET)

$(TARGET): $(OBJECTS)
	mkdir -p $(shell dirname $(TARGET))
	$(CXX) $(OBJECTS) -o $@ $(LDFLAGS)

