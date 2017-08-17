TARGETS = console-setup resolvconf mountkernfs.sh ufw plymouth-log screen-cleanup hostname.sh apparmor udev keyboard-setup cryptdisks cryptdisks-early iscsid networking hwclock.sh rpcbind open-iscsi mountdevsubfs.sh checkroot.sh urandom lvm2 checkfs.sh checkroot-bootclean.sh bootmisc.sh mountall-bootclean.sh mountall.sh kmod mountnfs-bootclean.sh mountnfs.sh procps
INTERACTIVE = console-setup udev keyboard-setup cryptdisks cryptdisks-early checkroot.sh checkfs.sh
udev: mountkernfs.sh
keyboard-setup: mountkernfs.sh udev
cryptdisks: checkroot.sh cryptdisks-early udev lvm2
cryptdisks-early: checkroot.sh udev
iscsid: networking
networking: resolvconf mountkernfs.sh urandom procps
hwclock.sh: mountdevsubfs.sh
rpcbind: networking
open-iscsi: networking iscsid
mountdevsubfs.sh: mountkernfs.sh udev
checkroot.sh: hwclock.sh keyboard-setup mountdevsubfs.sh hostname.sh
urandom: hwclock.sh
lvm2: cryptdisks-early mountdevsubfs.sh udev
checkfs.sh: cryptdisks lvm2 checkroot.sh
checkroot-bootclean.sh: checkroot.sh
bootmisc.sh: checkroot-bootclean.sh mountall-bootclean.sh udev mountnfs-bootclean.sh
mountall-bootclean.sh: mountall.sh
mountall.sh: checkfs.sh checkroot-bootclean.sh lvm2
kmod: checkroot.sh
mountnfs-bootclean.sh: mountnfs.sh
mountnfs.sh: networking rpcbind
procps: mountkernfs.sh udev
