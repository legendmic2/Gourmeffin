export default interface IPlace {
    id: string;
    name: string;
    address: string;
    placeType: string;
    description: string;
    rating: number;
}

export interface IPlaceList {
    data: IPlace[];
}