package mk.finki.ukim.emt.sharedkernel.domain.types;

import lombok.Getter;
import lombok.NonNull;
import mk.finki.ukim.emt.sharedkernel.domain.base.ValueObject;


import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Embeddable
@Getter
public class TypesCth implements ValueObject {

    @Enumerated(value = EnumType.STRING)
    private final TypesofClothes type;

    public TypesCth() {
        this.type =TypesofClothes.Blouses;
    }

    public TypesCth(@NonNull TypesofClothes type)
    {
        this.type=type;

    }
    public static TypesCth valueOf(TypesofClothes type)
    {
        return new TypesCth(type);
    }
}
