package agenduo.dto.response;

import agenduo.model.Couple;

public record CoupleResponse(Long id,
                             String UserAName,
                             String UserBName) {

    public CoupleResponse(Couple couple) {
        this(
                couple.getId(),
                couple.getUserA().getName(),
                couple.getUserB().getName()
        );
    }
}
