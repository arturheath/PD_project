import {clsx} from "clsx"
import {twMerge} from "tailwind-merge"

export function cn(...inputs) {
    // yo
    return twMerge(clsx(inputs))
}
