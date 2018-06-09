JDK_HOME ?= /usr/lib/jvm/java-8-openjdk-amd64

CFLAGS := -Wall -fPIC -I $(JDK_HOME)/include -I $(JDK_HOME)/include/linux
LDFLAGS := -fPIC -shared -lcncrypto

OBJECTS := \
	src/main/c/com_satoshiguild_xmr_util_Monero.o

TARGET := target/classes/libmonero.so

.PHONY: all clean

all: $(TARGET)

clean:
	$(RM) $(OBJECTS) $(TARGET)

$(TARGET): $(OBJECTS)
	mkdir -p $(shell dirname $(TARGET))
	$(CC) $< -o $@ $(LDFLAGS)

