SUMMARY = "${MACHINE} partitions bootargs files"
SECTION = "base"
PRIORITY = "required"
LICENSE = "CLOSED"
PR = "${SRCDATE}"
PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit deploy

SRC_URI = "http://downloads.openpli.org/archive/abcom/${MACHINE}-bootargs-${SRCDATE}.zip"

S = "${WORKDIR}"

ALLOW_EMPTY_${PN} = "1"
do_configure[nostamp] = "1"

do_deploy() {
    install -d ${DEPLOY_DIR_IMAGE}/abcom-partitions-${MACHINE}/${MACHINE}
    install -m 0755 ${WORKDIR}/bootargs.bin ${DEPLOY_DIR_IMAGE}/abcom-partitions-${MACHINE}
    install -m 0755 ${WORKDIR}/${MACHINE}/bootargs.bin ${DEPLOY_DIR_IMAGE}/abcom-partitions-${MACHINE}/${MACHINE}
}

addtask deploy before do_build after do_install

INSANE_SKIP_${PN} += "already-stripped"
