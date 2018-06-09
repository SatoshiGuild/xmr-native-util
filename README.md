# xmr-native-util

## Features

* CryptoNight hashes (both variants)
* Block blob manipulation

## Requirements

* Monero shared libraries built and put on your library path

## Installation

(if you haven't installed the monero libraries yet)

    sudo apt install git cmake build-essential libssl-dev pkg-config libboost-all-dev libzmq3-dev
    git clone https://github.com/monero-project/monero.git
    cd monero
    git checkout v0.12.2.0
    mkdir build
    cd $_
    cmake -DCMAKE_BUILD_TYPE=Release -DBUILD_SHARED_LIBS=1 ..
    make -j$(nproc)
    cd src
    sudo find . -iname "*.so" -not -iname "libversion.so" -exec cp -- "{}" /usr/lib \;

(running unit tests)

    sudo apt install openjdk-8-jdk maven
    git clone https://gitlab.com/satoshiguild/xmr-native-util.git
    cd xmr-native-util
    mvn test

## License

Released under the terms of the MIT license. See `LICENSE` for more information.
