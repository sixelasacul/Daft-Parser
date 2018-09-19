package com.tre;

public enum Headers {
    Id {
        @Override
        public String toString() {
            return "Id";
        }
    }, Type {
        @Override
        public String toString() {
            return "Type";
        }
    }, Address {
        @Override
        public String toString() {
            return "Address";
        }
    }, District {
        @Override
        public String toString() {
            return "District";
        }
    }, Distance {
        @Override
        public String toString() {
            return "Distance from work (m)";
        }
    }, DateEntered {
        @Override
        public String toString() {
            return "Date entered/renewed";
        }
    }, Views {
        @Override
        public String toString() {
            return "Views";
        }
    }, MoveInDate {
        @Override
        public String toString() {
            return "Available to move in";
        }
    }, Bedroom {
        @Override
        public String toString() {
            return "Bedroom";
        }
    }, OwnerOccupied {
        @Override
        public String toString() {
            return "Is owner occupied";
        }
    }, FlatmatesNumber {
        @Override
        public String toString() {
            return "Flatmates number";
        }
    }, FlatmatesInfo {
        @Override
        public String toString() {
            return "Flatmates info";
        }
    }, LookingFor {
        @Override
        public String toString() {
            return "Looking for";
        }
    }, Preferences {
        @Override
        public String toString() {
            return "Preferences";
        }
    }, Facilities {
        @Override
        public String toString() {
            return "Facilities";
        }
    }, Price {
        @Override
        public String toString() {
            return "Price (€)";
        }
    }, Per {
        @Override
        public String toString() {
            return "Per";
        }
    }, Bills {
        @Override
        public String toString() {
            return "Bills (€)";
        }
    }, BER {
        @Override
        public String toString() {
            return "BER";
        }
    }, Total {
        @Override
        public String toString() {
            return "Total/Month";
        }
    }, Notes {
        @Override
        public String toString() {
            return "Notes";
        }
    }, Contacted {
        @Override
        public String toString() {
            return "Contacted";
        }
    }, Answer {
        @Override
        public String toString() {
            return "Answer";
        }
    }
}
