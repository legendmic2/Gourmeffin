import UserStore from "./UserStore";
import PlaceStore from "./PlaceStore";
import ReviewStore from "./ReviewStore";

export default class RootStore {
    user = new UserStore(this);
    place = new PlaceStore(this);
    review = new ReviewStore(this);
}