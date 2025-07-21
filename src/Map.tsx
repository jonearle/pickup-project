import { useEffect, useRef } from 'react';
import L from 'leaflet';

// To render the map on the game page
export default function renderMap() {
  const mapRef = useRef<HTMLDivElement>(null);

  useEffect(() => {
    if (!mapRef.current) return;

    // Initalize map
    const map = L.map(mapRef.current).setView([44.65, -63.59], 12);

    L.tileLayer('https://tile.openstreetmap.org/{z}/{x}/{y}.png', {
        maxZoom: 19,
        attribution: '&copy; <a href="http://www.openstreetmap.org/copyright">OpenStreetMap</a>'
    }).addTo(map);

    // Add marker
    const marker = L.marker([44.65, -63.59]). addTo(map);

    return () => {map.remove()};
  }, []);

  return (
    <div ref={mapRef} style={{height: '200px', width: '350px'}}></div>
  );
}