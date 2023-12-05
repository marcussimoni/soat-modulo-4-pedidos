dockerhub_user=$1
image_name=soat-modulo-3
version=latest

if [ -z "$dockerhub_user" ]
then
  echo "Usuário do dockerhub não informado"
else
  docker build -t $image_name .

  docker tag $image_name:$version $dockerhub_user/$image_name:$version

  docker push $dockerhub_user/$image_name:$version

  echo "removendo a imagem após push para o docker hub"

  docker rmi -f $(docker images --filter "reference=$dockerhub_user/$image_name" -q)

  docker images $dockerhub_user/$image_name
fi
