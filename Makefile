JC=javac
EX=java

all: *.java
	$(JC) *.java
run: all
	$(EX) SELab03Driver
clean:
	rm *.class
