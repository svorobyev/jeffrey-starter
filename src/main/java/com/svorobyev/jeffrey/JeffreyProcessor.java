package com.svorobyev.jeffrey;

import com.svorobyev.jeffrey.annotations.JeffreyXlsReport;
import com.svorobyev.jeffrey.annotations.XlsColumn;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import java.util.LinkedHashSet;
import java.util.Set;

import static java.lang.String.format;

@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class JeffreyProcessor extends AbstractProcessor {

    private Types typeUtils;
    private Elements elementUtils;
    private Filer filer;
    private Messager messager;


    @Override
    public synchronized void init(ProcessingEnvironment env) {
        super.init(processingEnv);
        typeUtils = processingEnv.getTypeUtils();
        elementUtils = processingEnv.getElementUtils();
        filer = processingEnv.getFiler();
        messager = processingEnv.getMessager();
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment env) {
        //todo
        for (Element element : env.getElementsAnnotatedWith(JeffreyXlsReport.class)) {

            // Check if a class has been annotated with @JeffreyXlsReport
            if (element.getKind() != ElementKind.CLASS) {
                throw new IllegalArgumentException(
                        format("Only classes can be annotated with @%s", JeffreyXlsReport.class.getSimpleName()));
            }

            // We can cast it, because we know that it of ElementKind.CLASS
            TypeElement typeElement = (TypeElement) element;
            final XlsColumn[] jeffreyColumns = typeElement.getAnnotationsByType(XlsColumn.class);
            if (jeffreyColumns.length == 0) {
                throw new IllegalArgumentException(
                        format("@JeffreyXlsReport annotated class must have at least one field annotated with @%s",
                                XlsColumn.class.getSimpleName()));
            }


        }
        return true;
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> annotataions = new LinkedHashSet<>();
        annotataions.add(JeffreyXlsReport.class.getCanonicalName());
        annotataions.add(XlsColumn.class.getCanonicalName());
        return annotataions;
    }
}
