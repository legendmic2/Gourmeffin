import RootStore from "../stores/RootStore";
import PlaceApi from "./PlaceApi";
import UserApi from "./UserApi";
import {axiosUtil} from "../utils/AxiosUtil";

export default class RootApi {
    client = axiosUtil;

    place: PlaceApi;
    user: UserApi;

    // todo: user, review 추가

    // todo: AxiosUtil 생성

    constructor(store: RootStore) {
        this.place = new PlaceApi(this, store);
        this.user = new UserApi(this, store);
    }
}