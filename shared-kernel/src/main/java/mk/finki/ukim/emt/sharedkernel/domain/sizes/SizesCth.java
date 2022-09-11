package mk.finki.ukim.emt.sharedkernel.domain.sizes;

import lombok.Getter;
import lombok.NonNull;
import mk.finki.ukim.emt.sharedkernel.domain.base.ValueObject;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Embeddable
@Getter
public class SizesCth implements ValueObject {

    @Enumerated(value = EnumType.STRING)
    private final Sizes size;

    protected SizesCth()
    {
        this.size=Sizes.XS;
    }

    public SizesCth(@NonNull Sizes size)
    {
       this.size=size;

    }
    public static SizesCth valueOf(Sizes size)
    {
        return new SizesCth(size);
    }

}
