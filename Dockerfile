FROM maven
COPY . /employee
WORKDIR /employee
CMD ["mvn", "spring-boot:run"]