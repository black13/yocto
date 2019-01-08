# 🚀 Flashing the beaglebone by Romain 🤖

### Written by the master of markdown also known as David Guerin

![yocto.png](https://pbs.twimg.com/profile_images/1158899683/yocto-project-whitebg_400x400.png)

#### Introduction 🎁

Modifier le `local.conf` qui se trouve dans `/build/conf` depuis le dossier `pocky-rocko`, et y décommenter :

```
MACHINE ?= "beaglebone"
```

Cela permet du coup de choisir la machine cible de notre distribution.
Puis tout en bas de `local.conf` modifier aussi :

```
IMAGE_INSTALL_append += "example sl"
```

Cela permet de dire qu'au moment de la compilation de la distribution ces paquets seront disponible dans la distribution, cela active les packages des layers, ici : `example` et `sl`.

Ensuite, dans le fichier `bblayers.conf`, ajouter :

```
... \

/data_container/poky-rocko-18.0.0/meta-hello \
```

Cela active les layers.

Pour activer un patch, il faut modifier le `NOMRECETTE_VERSION.bb` qui se trouve dans le dossier de la recette :

```

...


SRC_URI =  " ... \
    file://bonjour.patch"

...

```

Pour compiler une recette, il faut remplir la fonction `do_compile()`, ici :

```
do_compile() {
    ${CC} ${LD_FLAGS} helloworld.c -o helloworld
}
```

Modifier `recipes-kernel/linx/linux-yocto_4.9.bbappend` :

```
COMPATIBLE_MACHINE = "beaglebone"
RDEPENDS_kernel-base += "kernel-devicetree"
DEPENDS += "xz-native bc-native"
KERNEL_DEVICETREE_beaglebone += " \
    bbb-4dcape431.dtb \
"

FILESEXTRAPATH_prepend := "${THISDIR}/linux-yocto_${LINUX_VERSION}:"

PV = "4.9.49"

SRC_URI += " \

```
