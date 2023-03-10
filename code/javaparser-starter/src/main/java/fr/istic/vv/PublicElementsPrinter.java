package fr.istic.vv;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.*;
import com.github.javaparser.ast.visitor.VoidVisitorWithDefaults;

import java.util.ArrayList;
import java.util.List;


// This class visits a compilation unit and
// prints all public enum, classes or interfaces along with their public methods
public class PublicElementsPrinter extends VoidVisitorWithDefaults<Void> {

    List<String> attributes = new ArrayList<>();
    List<String> getters = new ArrayList<>();

    @Override
    public void visit(CompilationUnit unit, Void arg) {
        for (TypeDeclaration<?> type : unit.getTypes()) {
            type.accept(this, null);
        }
    }

    public void visitTypeDeclaration(TypeDeclaration<?> declaration, Void arg) {

        if (!declaration.isPublic()) return;
        System.out.println(declaration.getFullyQualifiedName().orElse("[Anonymous]"));
        for (MethodDeclaration method : declaration.getMethods()) {
            method.accept(this, arg);
        }
        for(FieldDeclaration field : declaration.getFields()) {
            field.accept(this, arg);
        }
        // Printing nested types in the top level
        for (BodyDeclaration<?> member : declaration.getMembers()) {
            if (member instanceof TypeDeclaration)
                member.accept(this, arg);
        }
    }

    @Override
    public void visit(ClassOrInterfaceDeclaration declaration, Void arg) {
        visitTypeDeclaration(declaration, arg);
    }

    @Override
    public void visit(EnumDeclaration declaration, Void arg) {
        visitTypeDeclaration(declaration, arg);
    }

    @Override
    public void visit(MethodDeclaration declaration, Void arg) {
        if (!declaration.isPublic()) return;
        if (declaration.getName().toString().contains("get")){
            String nameGetter = declaration.getName().toString().toLowerCase().replaceAll("get", "");
            this.getters.add(nameGetter);
        }


        System.out.println("  " + declaration.getDeclarationAsString(true, true));
    }
    @Override
    public void visit(FieldDeclaration declaration, Void arg) {
        String fieldName = declaration.getVariables().get(0).toString();
        this.attributes.add(fieldName);
    }

    public void verifyGetters() {
        attributes.removeAll(getters);
        System.out.println("attribues without getters : " + attributes);
    }
}
