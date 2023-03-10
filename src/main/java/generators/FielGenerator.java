package generators;

import com.squareup.javapoet.FieldSpec;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Id;
import pojos.DevPoolAttribute;

import javax.lang.model.element.Modifier;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class FielGenerator {

    private AnnotationGenerator annotationGenerator = AnnotationGenerator.init();

    public static FielGenerator init() {
        return new FielGenerator();
    }
    public Set<FieldSpec> construct(Set<DevPoolAttribute> attributes) {
        Set<FieldSpec> fiels = new HashSet<>();
        for (DevPoolAttribute s : attributes) {
            fiels.add(contruct(s));
        }
        return fiels;
    }

    public FieldSpec contruct(DevPoolAttribute attribute) {

        switch (attribute.type().toLowerCase()) {
            case "boolean":
            case "bool":
                return FieldSpec.builder(Boolean.class, attribute.name())
                        .addModifiers(Modifier.PRIVATE)
                        .addAnnotations(annotationGenerator.addAnnotationsOnEntityFields(attribute))
                        .build();
            case "integer":
                return FieldSpec.builder(Integer.class, attribute.name())
                        .addAnnotations(annotationGenerator.addAnnotationsOnEntityFields(attribute))
                        .addModifiers(Modifier.PRIVATE)
                        .build();
            case "date":
                return FieldSpec.builder(Date.class, attribute.name())
                        .addAnnotations(annotationGenerator.addAnnotationsOnEntityFields(attribute))
                        .addModifiers(Modifier.PRIVATE)
                        .build();
            case "double":
                return FieldSpec.builder(Double.class, attribute.name())
                        .addAnnotations(annotationGenerator.addAnnotationsOnEntityFields(attribute))
                        .addModifiers(Modifier.PRIVATE)
                        .build();
            case "bigdecimal":
                return FieldSpec.builder(BigDecimal.class, attribute.name())
                        .addAnnotations(annotationGenerator.addAnnotationsOnEntityFields(attribute))
                        .addModifiers(Modifier.PRIVATE)
                        .build();
            case "objectid":
                return FieldSpec.builder(ObjectId.class, attribute.name())
                        .addModifiers(Modifier.PRIVATE)
                        .addAnnotation(Id.class)
                        .addAnnotations(annotationGenerator.addAnnotationsOnEntityFields(attribute))
                        .build();
            default:
                return FieldSpec.builder(String.class, attribute.name())
                        .addAnnotations(annotationGenerator.addAnnotationsOnEntityFields(attribute))
                        .addModifiers(Modifier.PRIVATE)
                        .build();
        }

    }

}
