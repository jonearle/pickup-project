import { useEffect, useRef } from 'react';
import L from 'leaflet';

// Type must be defined in advance or else the type of coords is undefined and will crash on loading
type MapProps = { coords: { x: number; y: number } };

// To render the map on the game page
export default function renderMap(props: MapProps) {
  const mapRef = useRef<HTMLDivElement>(null);

  useEffect(() => {
    if (!mapRef.current) return;

    // clear leftover leaflet id
    if ((mapRef.current as any)._leaflet_id) {
      (mapRef.current as any)._leaflet_id = null;
    }

    // Initalize map
    const map = L.map(mapRef.current).setView([props.coords.y, props.coords.x], 12);

    L.tileLayer('https://tile.openstreetmap.org/{z}/{x}/{y}.png', {
        maxZoom: 19,
        attribution: '&copy; <a href="http://www.openstreetmap.org/copyright">OpenStreetMap</a>'
    }).addTo(map);

    // Add marker
    const marker = L.marker([props.coords.y, props.coords.x]).addTo(map);

    return () => {map.remove()};
  }, [[props.coords.y, props.coords.x]]);

  return (
    <div ref={mapRef} style={{ height: '200px', width: '350px' }}/>
  );
}