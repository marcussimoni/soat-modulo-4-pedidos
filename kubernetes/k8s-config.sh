#!/bin/sh

declare -a DIRECTORIES
VALID_PARAM=0
if [ "$1" = "apply" ]
then
  VALID_PARAM=1
  DIRECTORIES+=("secrets")
  DIRECTORIES+=("postgres")
  DIRECTORIES+=("api")
fi

if [ "$1" = "delete" ]
then
  VALID_PARAM=1
  DIRECTORIES+=("api")
  DIRECTORIES+=("postgres")
  DIRECTORIES+=("secrets")
fi

if [ $VALID_PARAM -eq 1 ]
then
  for d in ${DIRECTORIES[@]}
    do
      echo "executando: kubectl $1 -f ./kubernetes/$d"
      kubectl $1 -f ./kubernetes/$d
    done
fi