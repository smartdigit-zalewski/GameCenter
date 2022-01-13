package com.smartdigit.zalewski.gamecenter.domain.enums;

public enum ShipStatus {
    POSITIONS_NOT_SET,
    POSITIONS_SET{
        @Override
        public boolean isReadyToPlay() {
            return true;
        }
    },
    DESTROYED {
        @Override
        public boolean isDestroyed() {
            return true;
        }
    };

    public boolean isDestroyed() {return false;}
    public boolean isReadyToPlay() {return false;}



}
