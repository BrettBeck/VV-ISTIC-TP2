# TCC *vs* LCC

Explain under which circumstances *Tight Class Cohesion* (TCC) and *Loose Class Cohesion* (LCC) metrics produce the same value for a given Java class. Build an example of such as class and include the code below or find one example in an open-source project from Github and include the link to the class below. Could LCC be lower than TCC for any given class? Explain.

## Answer

1.
The TCC involves dividing the number of method pairs that share at least one attribute by the total number of possible method pairs.
The LCC involves dividing the number of method pairs that are related to each other through one or more attributes by the total number of possible method pairs.
It follows that TCC is a subset of LCC. Consequently, LCC can not be lower than TCC for any given class.
The case where TCC and LCC produce the same value is when a method is connected to another through a path of length 1.

Code example where TCC == LCC :
public Class Test {
private int x ;
private int y ;

    public void a(int x){}
    public void b(int x){}
    
    public void c(int y){}
    public void d(int y){}
}
