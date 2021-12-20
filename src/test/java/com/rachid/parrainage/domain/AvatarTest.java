package com.rachid.parrainage.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.rachid.parrainage.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class AvatarTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Avatar.class);
        Avatar avatar1 = new Avatar();
        avatar1.setId(1L);
        Avatar avatar2 = new Avatar();
        avatar2.setId(avatar1.getId());
        assertThat(avatar1).isEqualTo(avatar2);
        avatar2.setId(2L);
        assertThat(avatar1).isNotEqualTo(avatar2);
        avatar1.setId(null);
        assertThat(avatar1).isNotEqualTo(avatar2);
    }
}
