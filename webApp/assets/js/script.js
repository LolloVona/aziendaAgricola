const hamburger = document.querySelector('.hamburger');
const menu = document.querySelector('.menu');

hamburger?.addEventListener('click', () => {
  menu.classList.toggle('open');
});

const heroImages = [
  'https://wp-themes.com/wp-content/themes/green-vegetable-store/images/slider-img1.png',
  'https://wp-themes.com/wp-content/themes/green-vegetable-store/images/slider-img2.png',
  'https://wp-themes.com/wp-content/themes/green-vegetable-store/images/slider-img3.png'
];
let current = 0;
const product = document.querySelector('.hero__product');

function changeSlide(direction) {
  current = (current + direction + heroImages.length) % heroImages.length;
  product.style.opacity = '0';
  setTimeout(() => {
    product.src = heroImages[current];
    product.style.opacity = '1';
  }, 180);
}

document.querySelector('.arrow--next')?.addEventListener('click', () => changeSlide(1));
document.querySelector('.arrow--prev')?.addEventListener('click', () => changeSlide(-1));
