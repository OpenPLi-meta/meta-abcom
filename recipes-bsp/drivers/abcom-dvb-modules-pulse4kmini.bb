KV = "4.4.35"
SRCDATE = "20220315"

PROVIDES = "virtual/blindscan-dvbs"

require abcom-dvb-modules.inc

SRC_URI[md5sum] = "9ce45c0cbf58204462038fb2403c6194"
SRC_URI[sha256sum] = "0eacd706184697acb278428d2a2314b7d813e732ce90650d227b08bc57b80110"

COMPATIBLE_MACHINE = "pulse4kmini"

INITSCRIPT_NAME = "suspend"
INITSCRIPT_PARAMS = "start 89 0 ."
inherit update-rc.d

do_configure[noexec] = "1"

# Generate a simplistic standard init script
do_compile_append () {
	cat > suspend << EOF
#!/bin/sh

runlevel=runlevel | cut -d' ' -f2

if [ "\$runlevel" != "0" ] ; then
	exit 0
fi

mount -t sysfs sys /sys

/usr/bin/turnoff_power
EOF
}

do_install_append() {
	install -d ${D}${sysconfdir}/init.d
	install -d ${D}${bindir}
	install -m 0755 ${S}/suspend ${D}${sysconfdir}/init.d
	install -m 0755 ${S}/turnoff_power ${D}${bindir}
}

do_package_qa() {
}

FILES_${PN} += " ${bindir} ${sysconfdir}/init.d"

INSANE_SKIP_${PN} += "already-stripped"
