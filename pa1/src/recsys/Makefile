JFLAGS = -g
JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
	KeyValue.java \
	User.java \
	Item.java \
	LoadInput.java \
	UserBasedRecommender.java \ 
	Recommender.java 

MAIN = Recommender

default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class
