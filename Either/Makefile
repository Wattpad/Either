NULL :=

GENERATED_SRCS := generated-srcs
SRCS := srcs

DJINNI_DIR := ../djinni
DJINNI_BIN := $(DJINNI_DIR)/src/run
DJINNI_SUPPORT_DIR := $(DJINNI_DIR)/support-lib

JSONCPP_DIR := ../jsoncpp

ANDROID_DIR := ../EitherAndroid
ANDROID_JNI_DIR := $(ANDROID_DIR)/app/src/main/jni
ANDROID_JAVA_DIR := $(ANDROID_DIR)/app/src/main/java

ANDROID_GENERATED_PKG_PREFIX := com/wattpad/api/

JAVA_OPTS := \
	--java-out $(GENERATED_SRCS)/java \
	--java-package com.wattpad.api \
	--java-either-class Either \
	--java-either-package com.wattpad.util \
	$(NULL)

JNI_OPTS := \
	--jni-out $(GENERATED_SRCS)/jni \
	--ident-jni-class NativeFooBar \
	--ident-jni-file NativeFooBar \
	$(NULL)

CPP_OPTS := \
	--cpp-out $(GENERATED_SRCS)/cpp \
	--cpp-optional-header "<boost/optional.hpp>" \
	--cpp-optional-template "boost::optional" \
	--cpp-either-header "\"either.hpp\"" \
	--cpp-either-template "either" \
	$(NULL)

OBJC_OPTS := \
	--objc-out $(GENERATED_SRCS)/objc \
	--objc-type-prefix WP \
	--objc-either-header "WPEither.h" \
	--objc-either-class "WPEither" \
	--objcpp-namespace "djinni" \
	$(NULL)

DJINNI = $(DJINNI_BIN) $(JAVA_OPTS) $(JNI_OPTS) $(CPP_OPTS) $(OBJC_OPTS)

DJINNI_SRCS := \
	idl/eithertest.djinni \
	$(NULL)

build: $(DJINNI_SRCS)
	mkdir -p $(GENERATED_SRCS)
	$(DJINNI) --idl $<

GENERATED_CPP_FILES = \
	$(wildcard $(GENERATED_SRCS)/cpp/*.cpp) \
	$(wildcard $(GENERATED_SRCS)/cpp/*.hpp) \
	$(NULL)

GENERATED_JNI_FILES = \
	$(wildcard $(GENERATED_SRCS)/jni/*.cpp) \
	$(wildcard $(GENERATED_SRCS)/jni/*.hpp) \
	$(NULL)

GENERATED_JAVA_FILES = \
	$(wildcard $(GENERATED_SRCS)/java/*.java) \
	$(NULL)

SUPPORT_CPP_FILES = \
	$(wildcard $(DJINNI_SUPPORT_DIR)/cpp/*.cpp) \
	$(wildcard $(DJINNI_SUPPORT_DIR)/cpp/*.hpp) \
	$(NULL)

SUPPORT_JNI_FILES = \
	$(wildcard $(DJINNI_SUPPORT_DIR)/jni/*.cpp) \
	$(wildcard $(DJINNI_SUPPORT_DIR)/jni/*.hpp) \
	$(NULL)

SUPPORT_JAVA_FILES = \
	$(wildcard $(DJINNI_SUPPORT_DIR)/java/*) \
	$(NULL)

IMPL_CPP_FILES = \
	$(wildcard $(SRCS)/*.cpp) \
	$(wildcard $(SRCS)/*.hpp) \
	$(NULL)

# Copies over files to the Android project after generating them
dist: build $(DJINNI_DIR)
	mkdir -p $(ANDROID_JNI_DIR)
	echo "Copying generated cpp files"
	cp $(GENERATED_CPP_FILES) $(GENERATED_JNI_FILES) $(ANDROID_JNI_DIR)/
	echo "Copying support cpp files"
	cp $(SUPPORT_CPP_FILES) $(SUPPORT_JNI_FILES) $(ANDROID_JNI_DIR)/
	echo "Copying impl cpp files"
	cp $(IMPL_CPP_FILES) $(ANDROID_JNI_DIR)/
	echo "Copying JsonCpp files"
	cp -R $(wildcard $(JSONCPP_DIR)/*) $(ANDROID_JNI_DIR)/
	mkdir -p $(ANDROID_JAVA_DIR)/$(ANDROID_GENERATED_PKG_PREFIX)
	cp $(GENERATED_JAVA_FILES) $(ANDROID_JAVA_DIR)/$(ANDROID_GENERATED_PKG_PREFIX)
	cp -R $(SUPPORT_JAVA_FILES) $(ANDROID_JAVA_DIR)/

all: dist

clean:
	rm -rf $(GENERATED_SRCS)
