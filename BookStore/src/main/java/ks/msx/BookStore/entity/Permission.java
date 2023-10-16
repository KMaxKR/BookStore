package ks.msx.BookStore.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {
    USER("USER"),
    ADMIN("ADMIN");

    @Getter
    public final String permission;
}
