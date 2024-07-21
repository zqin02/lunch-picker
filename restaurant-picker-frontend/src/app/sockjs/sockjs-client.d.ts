//to resolve global undefined https://github.com/sockjs/sockjs-client/issues/439

declare module 'sockjs-client/dist/sockjs' {
  import SockJS from '@types/sockjs-client';

  export = SockJS;
  export as namespace SockJS;
}