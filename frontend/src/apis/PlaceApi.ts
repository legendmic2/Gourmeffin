import RootApi from "./RootApi";
import RootStore from "../stores/RootStore";
import IPlace, {IPlaceList} from "../types/Place";

export default class PlaceApi {
    constructor(private api: RootApi, private store: RootStore) {
    }

    async getAll() {
        const res = await this.api.client.get(`/place`); // todo: AxiosResponse 이용하여 AxiosUtil 생성
        this.store.place.load(res.data.data);
    }

    async create(newPlace: IPlace) {
        const res = await this.api.client.put<IPlaceList>(`/place`);

        const createdPlace = res.data.data.find(place => place.name === newPlace.name);

        if(createdPlace)  {
            this.store.place.add(createdPlace);
        }

        if(res.data) {

        }

    }
}