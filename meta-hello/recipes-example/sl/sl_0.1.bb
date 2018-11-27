#
# This file was derived from the 'Hello World!' example recipe in the
# Yocto Project Development Manual.
#

SUMMARY = "Sl application"
SECTION = "examples"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENCE;md5=5cf91496cd2b30b1d752c64d1b269f01"

SRC_URI = "git://github.com/mtoyoda/sl.git"
SRCREV = "master"

DEPENDS += "ncurses"
S = "${WORKDIR}/git"

do_compile() {
#             ${CC} ${LDFLAGS} helloworld.c -o helloworld
#	make
	${CC} ${LDFLAGS} -o sl sl.c -lncurses
}

do_install() {
             install -d ${D}${bindir}
             install -m 0755 sl ${D}${bindir}
}

