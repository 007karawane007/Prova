;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-lambda-reader.ss" "lang")((modname percorsi-manattan2d_3d) (read-case-sensitive #t) (teachpacks ((lib "drawings.ss" "installed-teachpacks"))) (htdp-settings #(#t constructor repeating-decimal #f #t none #f ((lib "drawings.ss" "installed-teachpacks")) #f)))
(define paths ;val: intero
  (lambda (i j) ;i, j: interi
    (cond ((and (= i 0) (> j 0)) 1)
          ((and (> i 0) (= j 0)) 1)
          (else
           (+ (paths  i (- j 1)) (paths (- i 1) j))
           )
          )
    ))

(define paths-3d ;val: intero
  (lambda (i j k) ;i, j, k: interi
    (cond ((and (> i 0) (> j 0) (= k 0))
           (paths i j))
          ((and (> i 0) (= j 0) (> k 0))
           (paths i k))
          ((and (= i 0) (> j 0) (> k 0))
           (paths j k))
          ((or (and (> i 0) (= j 0) (= k 0)) (and (= i 0) (> j 0) (= k 0)) (and (= i 0) (= j 0) (> k 0)))
           1)
          ((and (= i 0) (= j 0) (= k 0))
           0)
          (else
           (+ (paths-3d i j (- k 1)) (paths-3d i (- j 1) k) (paths-3d (- i 1) j k))
           )
          )
    ))